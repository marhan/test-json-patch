# test-json-patch

A simple REST API for testing json-patch

## Build and Run

```bash
./gradlew clean build bootRun
```

## Test

The following curl commands can be used to test the json-patch library.

Test DIFF:

```bash
curl -X "POST" -v localhost:8080/diff -H "Content-Type: application/json" -d '{"source":[{"name":"a"},{"name":"b"},{"name":"c"}],"target":[{"name":"b"}]}'
```

Test PATCH:

```bash
curl -X "POST" -v localhost:8080/patch -H "Content-Type: application/json" -d '{"source":[{"name":"a"},{"name":"b"},{"name":"c"}],"patch":[{"op":"remove","path":"/0"},{"op":"remove","path":"/2"}]}'
```

