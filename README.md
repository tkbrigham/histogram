# Description
##### NOTE: first iteration

A small app that:
  1. Takes a target directory as (currently) a command-line parameter
  2. Recursively searches for all .zip files and unzips them into a new folder
   inside the .zip file parent folder
  3. Recursively finds all .txt files starting with the target directory
  4. Counts all words found in all .txt files
  5. Opens a (Google template) histogram that plots the found words in a fun way

# Prerequisites
- `scala` and `scalac` binaries (written/compiled/tested on v2.11.8)
- git

# How to run
In terminal, run the following commands:
```sh
git clone git@github.com:tkbrigham/histogram.git
cd histogram
scalac *.scala
scala histogram.Main path/to/target/directory
```

# Known Issues/Areas for Improvement
- No (actual) tests. Test folder used for informal testing.
- Needs: Shell script wrapper for nicer interface (prompt for input etc)
- (Probably) Not (at all) in Scala-style
- Numbers-as-strings are included in histogram (ie. file with line "2 plus 2
  equals 4" will map the string "2" as occurring twice)
- Does not include hidden files
- App takes no options to alter graph dimensions/settings
  - Different amounts of words/frequencies have also not been tested
- There's probably a nicer way to have .html template and inject scala strings,
  but `XML.loadFile` didn't seem to be the solution
- Should binaries be added to git?
