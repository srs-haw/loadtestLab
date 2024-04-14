# Simple Loadtest-Application
inspired by https://callistaenterprise.se/blogg/teknik/2014/04/16/a-first-look-at-gatling-a-dsl-based-load-test-tool/

and https://tempered.works/posts/2019/01/07/performance-with-spring-boot-and-gatling-part-1/

Stefan Sarstedt, HAW Hamburg

## Start Test-Application
`./gradlew bootRun`

Try out the API: `curl "http://localhost:8080/process?minMs=500&maxMs=1000"`

## Start our Loadtest (in a separate terminal)
`./gradlew gatlingRun-com.haw.srs.loadtest.LoadTest`