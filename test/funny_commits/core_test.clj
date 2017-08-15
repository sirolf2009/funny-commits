(ns funny-commits.core-test
  (:require [clojure.test :refer :all]
            [funny-commits.core :refer :all]))

(deftest funny
  (testing "fuck is funny"
    (is funny? "fuck")))
