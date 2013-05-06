LSEQ maven repository
=====================
To use the LSEQ framework in another application using maven, modify the pom.xml.

First add the repository:
```xml
<repositories>
    <repository>
        <id>LSEQ-mvn-repo</id>
        <url>https://raw.github.com/Chat-Wane/LSEQ/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```

Second add the dependency:
```xml
<dependencies>
    <dependency>
        <groupId>alma.fr</groupId>
        <artifactId>LSEQ</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
