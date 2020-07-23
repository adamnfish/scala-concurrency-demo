Scala concurrency
=================

Testing changes to Scala's global Execution Context that landed in
Scala 2.13.x.

## Usage

There are two projects in this repo.

### slow-server

This is a simple HTTP server that takes ~2secs to respond to requests.

    $ sbt
	> project slowServer
	> run

This will start the server in port 7000.

#### demo

A demo application that makes parallel calls to this slow API.

    $ sbt
	> project demo
	> run

If the requests were executed serially, this will be clearly visible
given the slow response times.
