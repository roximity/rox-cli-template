(ns {{namespace}}.actions.run
  (:require [yoyo :refer  [ylet]]
      [{{namespace}}.core :refer [options]]
    )
  )

(defn make-system 
  "Latch is passed in by yoyo."
  [latch]
  (ylet []
    (println "RUNNING with " @{{namespace}}.core/options)
    (System/exit 0)
    (latch)
    )
  )
