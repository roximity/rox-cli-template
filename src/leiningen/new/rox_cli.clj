(ns leiningen.new.rox-cli
  (:require [leiningen.new.templates :refer [renderer year project-name 
              multi-segment name-to-path ->files sanitize-ns]]
            [leiningen.core.main :as main]))

(def render (renderer "rox-cli"))

(defn rox-cli
  "FIXME: write documentation"
  [name]
  (let [main-ns (multi-segment (sanitize-ns name))
        data {:raw-name name
              :name (project-name name)
              :namespace main-ns
              :sanitized (name-to-path name)
              :nested-dirs (name-to-path main-ns)
              :year (year)}
        ]

    (main/info "Generating fresh 'lein new' rox-cli project.")

    (->files data
       [".gitignore" (render "gitignore" data)]
       [".env" (render "dotenv" data)]
       ["project.clj" (render "project.clj" data)]
       ["README.md" (render "README.md" data)]
       ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
       ["src/{{nested-dirs}}/actions/run.clj" (render "run.clj" data)]
       ["test/{{nested-dirs}}/core_test.clj" (render "test.clj" data)]
      )))

