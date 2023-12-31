; Copyright 2023 Vivid Inc. and/or its affiliates.
;
; Licensed under the Apache License, Version 2.0 (the "License")
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;     https://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.

(defproject net.vivid-inc/peppy-pipeline "0.1.0"

  :description "Peppy Pipeline, a vivacious asset pipeline and build tool."
  :license {:distribution :repo
            :name         "Apache License 2.0"
            :url          "https://www.apache.org/licenses/LICENSE-2.0"}
  :scm {:dir  ".."
        :name "git"
        :tag  "0.1.0"
        :url  "https://github.com/vivid-inc/peppy-pipeline"}
  :url "https://github.com/vivid-inc/peppy-pipeline"

  :aliases {"build"     ["do"
                         ["version"]
                         ["clean"]
                         ["eftest"]
                         ;["cloverage"]
                         ;["clj-kondo"]
                         ["jar"]
                         ["install"]]
            "clj-kondo" ["with-profile" "clojure-1.11.1,clj-kondo" "run" "-m" "clj-kondo.main" "--"
                         "--lint" "src:test"
                         "--parallel"]
            "lint"      ["do"
                         ["cljfmt" "check"]
                         ["clj-kondo"]
                         ["antq"]
                         ["nvd" "check"]]}

  :dependencies [[babashka/process      "0.5.21"]
                 [hawk                  "0.2.11"]
                 [prismatic/plumbing    "0.6.0"]
                 [org.suskalo/farolero  "1.5.0"]]

  :eval-in-leiningen true

  :exclusions [org.clojure/clojure]

  :global-vars {*warn-on-reflection* true}

  :javac-options ["-target" "null"]

  :main vivid.peppy

  :manifest {"Built-By" "vivid"}

  ; This version of Leiningen is what we have available to us in CI.
  :min-lein-version "2.9.8"

  ; Enable this to assist with determining :excludes whenever dependencies and
  ; plugins change, then re-disable it.
  ;:pedantic? :abort

  :plugins [[com.github.liquidz/antq "RELEASE" :exclusions [commons-codec
                                                            org.apache.httpcomponents/httpclient
                                                            org.codehaus.plexus/plexus-utils
                                                            org.slf4j/slf4j-api]]
            [lein-cljfmt    "0.9.2"]
            [lein-cloverage "1.2.4"]
            [lein-eftest    "0.6.0"]
            [lein-nvd       "1.4.1"   :exclusions [com.fasterxml.jackson.core/jackson-annotations
                                                   commons-io
                                                   org.apache.commons/commons-lang3
                                                   org.codehaus.plexus/plexus-utils
                                                   org.slf4j/jcl-over-slf4j
                                                   org.slf4j/slf4j-api]]]

  :repl-options {:init-ns vivid.peppy}

  :repositories [["clojars" {:sign-releases false}]]

  :source-paths ["src/main/clj"])
