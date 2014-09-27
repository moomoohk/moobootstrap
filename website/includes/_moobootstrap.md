#MooBootstrap

MooBootstrap is a [bootstrap](http://stackoverflow.com/questions/1254542/what-is-bootstrapping) I wrote in Java which makes update detection and self updating really easy.

Check out the [README.md](https://github.com/moomoohk/moobootstrap/blob/master/README.md) for an overview.

##Getting started

MooBootstrap works with an XML file (hereby referred to as “changelog.xml”) which contains all your changelog information. Your changelog.xml file needs to follow a certain format so that MooBootstrap can understand it and extract the information. It should look like so:				

    <bootstrapChangelog>
        <remoteBuildNumber>2</remoteBuildNumber>
        <changeLog>
            <build buildNumber="1">
                <buildVersionString>1.0</buildVersionString>
                <changes>
                    + Initial version
                </changes>
            </build>
            <build buildNumber="2">
                <buildVersionString>2.0</buildVersionString>
                <changes>
                    + Added a feature
                    - Removed a feature
                    * Changed a feature
                </changes>
            </build>
        </changeLog>
    </bootstrapChangelog>

_Notes:_

1. You can have as many builds as you want. You’re not limited to 2 as in the example
2. The `<remoteBuildNumber>` element should contain the number of the latest build from the `<build>` list
3. You can write anything in the `<changes>` element but I recommend following the format I used above
4. It is recommended that your elements are kept in the same order as the example so that your changelog.xml will be forward compatible with newer versions of the bootstrap

Once you've hosted your changelog.xml on a server you can start writing the client which interacts with it.



##//TODO

* Integrate XML schemas somehow (.xsd) and maybe write a tool which makes it easy to create and maintain changelog.xml
* Extend the API to support more features (not sure what but I feel like it could do more)