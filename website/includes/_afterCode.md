_Notes:_

1. You can have as many builds as you want. You’re not limited to 2 as in the example
2. The `<remoteBuildNumber>` element should contain the number of the latest build from the `<build>` list
3. You can write anything in the `<changes>` element but I recommend following the format I used above

Once you've hosted your changelog.xml on a server you can start writing the client which interacts with it.



##//TODO

* Integrate XML schemas somehow (.xsd) and maybe write a tool which makes it easy to create and maintain changelog.xml
* Extend the API to support more features (not sure what but I feel like it could do more)