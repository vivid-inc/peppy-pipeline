; Copyright 2022 Vivid Inc. and/or its affiliates.

; Licensed under the Apache License, Version 2.0 (the "License");
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;    https://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.

; TODO Load everything into a queue. Process when at max capacity or contents stabilizes after 100ms debounce (configurable).
; TODO Pass-through copying of files.
; TODO Observe the configuration file, hot reload it on changes, reject reload when there are config errors.
; TODO Re-run graph in response to Observer input.
; TODO When first started, do all input files. Use file date times to see if output needs updating.
; TODO step functions: in record -> out transcript
; TODO :continuous mode, :once mode.

(ns vivid.peppy
  (:require
    [vivid.peppy.plugin.gzip]
    [vivid.peppy.log :as log])
  (:import
    (java.nio.file Path)))

(defn main [args]
  (log/*info-fn* "Peppy getting straight to work")
  (vivid.peppy.plugin.gzip/watch (:gzip args))
  (while true
    (Thread/sleep Long/MAX_VALUE)))