Riak Bucket Nuker
=================

## Requirements ##
Requires my fork of the Scalapeños Riak client

1. clone https://github.com/kender-janrain/riak-scala-client
2. cd riak-scala-client
3. sbt publish-local

## Usage ##
To nuke some buckets:

	cd riak-bucket-nuker
	sbt "run bucket1 bucket2 bucket3"
