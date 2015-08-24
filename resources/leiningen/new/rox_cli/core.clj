(ns {{namespace}}.core
  (:require 
    [clojure.tools.cli :refer  [parse-opts]]
    [clojure.string :as string]
    [yoyo :refer [ylet] :as yoyo]
    )
  (:gen-class))

(def cli-options
  [;; First three strings describe a short-option, long-option with optional
   ;; example argument description, and a description. All three are optional
   ;; and positional. Can use :default, :parse-fn, :validate, :assoc-fn
   ;; https://github.com/clojure/tools.cli
   ["-h" "--help"]])

(def options (atom {}))

(defn usage  [options-summary]
  (->>  ["TODO Fix description"
        ""
        "Usage: {{name}} [options] action"
        ""
        "Options:"
        options-summary
        ""
        "Actions:"
        "  run     Run the program"
        ]
       (string/join \newline)))

(defn error-msg  [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)
        action    (first arguments)
        system-fn (symbol (str "{{namespace}}.actions." action "/make-system"))
      ]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 1) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Execute program with options
    (reset! {{namespace}}.core/options options)
    (println "Starting: " action)
    (yoyo/set-system-fn! system-fn)
    (yoyo/start!)
    )
  ) 
 

