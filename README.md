# funny-commits

This is an application that checks new github commits and searches for keywords like fuck, shit or nazi. This is really just for entertainment purposes and for me to practice in clojure

## Usage

clone the repository, point a shell to the folder an run
```bash
lein run <GIT-USERNAME> <GIT-PASSWORD>
```
Replace the placeholders with your own credentials. The reason they're there is because github severely limits the amount of calls you can make when not logged in

## Example output
```
Searching for funny commits
(greenkeeper[bot]: chore(package): update tslint-config-holy-grail to version 10.0.0
https://github.com/chrisguttandin/metadata-detector-broker/commits/c8cb3340bc26c9bc29c7b737a2f4e2273fd5dc0b)
(Daniel Saunders: fuck go back
https://github.com/PupperWoff/NPFchan/commits/04ce72f24b7be1be6599e36d24c3083719d58581)
```
