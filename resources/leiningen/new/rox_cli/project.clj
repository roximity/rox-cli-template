(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://roximity.com"
  :plugins [[lein-midje "3.1.3"]]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [jarohen/yoyo "0.0.5"]
                 [org.clojure/tools.cli "0.3.3"]
                 [clj-time "0.10.0"]
                 [com.novemberain/monger "3.0.0"]
                 [org.mongodb/mongodb-driver "3.0.2"]
                 [org.clojure/data.csv "0.1.3"]]
  :main ^:skip-aot {{namespace}}.core
  :source-path "src"
  :target-path "target/%s"
  :profiles {
    :uberjar {:aot :all}
    :dev {
      :plugins [[lein-dotenv "RELEASE"]]
      :dependencies [[midje "1.6.3"]]
    }
    :repl {
      :dependencies [[midje "1.6.3"]]
    }
  })
