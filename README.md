# Unomi Plugin Jahia - Maven Archetype (Non official)

## Overview
To create an Unomi - Jahia plugin from scratch, the easiest way is to use the **unomi-jahia-archetype**. 
It will generate a new maven project with a sample rule (JSON) and a sample action (JSON and Class).

The project structure will be:

    <ARTIFACT-ID>
    ├── pom.xml
    └── src
        ├── main
        |   ├── java
        |   |   └── org
        |   |      └── apache
        |   |         └── unomi
        |   |            └── <PLUGIN-NAME>Action.java
        |   └── resources
        |       ├── META-INF
        |       |   └── cxs
        |       |       ├── actions
        |       |       |   └── <PLUGIN-NAME>.json 
        |       |       └── rules
        |       |           └── <PLUGIN-NAME>.json
        |       |
        |       └── OSGI-INF
        |           └── blueprint
        |               └── blueprint.xml
        └── test 
            └── java
                └── org
                   └── apache
                      └── unomi
                         └── <PLUGIN-NAME>ActionTest.java
                         
## How to use it
```bash
$ mvn archetype:generate  -DarchetypeGroupId=org.apache.unomi -DarchetypeArtifactId=uunomi-jahia-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=org.apache.unomi -DartifactId=<ARTIFACT-ID>
```
During execution you will be prompted to confirm the default values. 
 * **version** will default to the last version
 * **plugin-name** will default to `SamplePlugin`
 

```bash
[INFO] Using property: groupId = org.apache.unomi
[INFO] Using property: artifactId = test
[INFO] Using property: version = 1.3.2-jahia
[INFO] Using property: package = org.apache.unomi
[INFO] Using property: plugin-name = SamplePlugin
Confirm properties configuration:
groupId: org.apache.unomi
artifactId: test
version: 1.3.2-jahia
package: org.apache.unomi
plugin-name: SamplePlugin
 Y: :
```
Answer no ('n' or 'N') to edit the default values for example:

```bash
Define value for property 'version' 1.3.2-jahia: : 
Define value for property 'package' org.apache.unomi: : 
Define value for property 'plugin-name' SamplePlugin: : test
```
Press **ENTER** to validate default values, or provide new value and then press **ENTER**.
Press **ENTER** to confirm provided values, and bingo you have your plugin generated.

## Build and deploy your plugin

All you have to do to build your plugin is:

```bash
$ mvn clean install
```
Use **deploy** if you use a remote maven repository.
To deploy your plugin in Unomi, you need to connect to the Apache Karaf SSH Console 
(Karaf is the OSGI container used by Unomi) and the install your bundle:

```bash
karaf@root()> bundle:install mvn:org.apache.unomi/<ARTIFACT-ID>/<VERSION>

karaf@root()> la
```
Get the bundle ID from the list and the start your bundle:

```bash
karaf@root()> start <BUNDLE-ID>
```

### Test your new plugin

You can test the sample plugin rule and action.

Get a new session if you don't have one by calling the context.json

```bash
GET http://localhost:8181/context.js?sessionId=1234
```
Then send the event to Unomi via the eventCollector

```bash
POST http://localhost:8181/eventcollector?sessionId=1234
{
  "events": [
    {
      "eventType": "<PLUGIN-NAME>Event"
    }
  ]
}
```
If everything is ok you will see in the Unomi log:

```bash
org.apache.unomi.<ARTIFACT-ID> - <VERSION> | <PLUGIN-NAME>Action : Executed successfuly with params
org.apache.unomi.<ARTIFACT-ID> - <VERSION> | Parameters : {param1=value1, param2=value2, value3, value4}
```

**param1** is a single value, however **param2** is a multivalue.
