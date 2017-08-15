(ns funny-commits.core
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:gen-class))

(defmacro forever [& body]
  `(while true ~@body))

(defn request [url]
  (client/get url {:basic-auth [(nth *command-line-args* 0) (nth *command-line-args* 1)] :as :json}))

(defn get-rate []
  (-> (request "https://api.github.com/rate_limit") :body :resources :core :remaining)
  )

(def funny-words #"(?i).*(fuck|shit|nazi|cunt|pussy|slayer|damn|holy).*")
(defn funny? [message]
  (do
    (not (empty? (re-matches funny-words message)))))

(defn events []
  (:body (request "https://api.github.com/events")))

(defn commits [events]
  (def commits-raw (map #(-> % :payload :commits) events))
  (def commits-real (filter #(not (nil? %)) commits-raw))
  commits-real
  (def commits-bare (map #(select-keys % [:message :url :author]) (flatten commits-real)))
  commits-bare)

(defn funny-commits [commits]
  (->> commits
       (filter #(not (nil? (:message %))))
       (filter #(funny? (:message %)))))

(defn as-html-commit [api-commit]
  (clojure.string/replace api-commit #"https://api.github.com/repos/" "https://github.com/")
  )

(defn -main
  [& args]
  (println "Searching for funny commits")
  (forever
    (do
      (def current-events (events))
      (def current-commits (commits current-events))
      current-commits
      (def current-funny (funny-commits current-commits))
      current-funny
      (if (not (empty? current-funny))
        (println (map #(str
                         (-> % :author :name) ": " (-> % :message) "\n" (as-html-commit (:url %))
                         ) current-funny)))
      (Thread/sleep 1000))))

(-main)
