# serenity-rest-test
Example of test framework besed on Serenity, RestAssured and Cucumber

Run tests via maven:
mvn clean verify

Debug tests via cucumber feature:
VM Options settings
-DforkCount=0 -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.showdatetime=true -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG -Dorg.apache.commons.logging.simplelog.log.org.apache.http.wire=ERROR