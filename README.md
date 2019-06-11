# MGM Experiences



## Build


### Prerequisites

* Maven
* [PostgreSQL](https://www.postgresql.org/) installed locally or on docker (instruction down below)

### [PostgresSQL on Docker](https://hub.docker.com/_/postgres)
#### Pull the image

```
 docker pull postgres:{tag}
```

*    **\{tag\}**: [The image tag/version](https://hub.docker.com/_/postgres#supported-tags-and-respective-dockerfile-links). Eg: *latest*, *11*,...

#### Start the container

```
docker run --name {container_name} -e POSTGRES_USER={POSTGRES_USER} -e POSTGRES_PASSWORD={POSTGRES_PASSWORD} -e POSTGRES_DB={POSTGRES_DB} -p {port}:5432 -d postgres
```

*    **\{container_name\}**: The name for the container you want to create.
*    **\{port\}**: Your local port you wish to use for Postgres.
*    **{POSTGRES_USER}**: Set the name of the default PostgreSQL superuser
*    **{POSTGRES_PASSWORD}**: Set default password for the user above
*    **{POSTGRES_DB}**: Set the name of the default PostgreSQL database
     



### Clone git

```
git clone https://bitbucket.mgm-tp.com/scm/exp/main.git
cd main
```

### Steps

*    Edit SQL configurations appropriately to your environment ( *.properties* files in *src\\main\\resources* ):
```
src\main\resources\dbschema\liquibase-dev.properties
src\main\resources\dbschema\liquibase-prod.properties
src\main\resources\application.properties
```
*    Generate/update tables with Liquibase if necessary:

For development:

```
mvn liquibase:update -P development
```

For production:

```
mvn liquibase:update -P production
```

*    Build:

```
mvm clean install
```

## Run/Debug locally

### Prerequisites


* Intellij IDEA Ultimate
* [Tomcat](https://tomcat.apache.org/) downloaded and extracted

### Configuration
* At the top right corner of the IDE, click **Add Configuration**.

![Configuration](images/cfg.PNG)

* Click the **Plus (+)** and choose **Tomcat Server** > **Local**

![Add New Configuration](images/add_cfg.png)

* Click **Configure...**

![Configure...](images/cfg_main.PNG)

* Set **Tomcat Home** to your extracted tomcat directory then click **OK**.

![Set directory](images/set_dir.PNG)

* Choose **Deployment** tab then click the **Plus (+)** and choose the **mgm-experiences:war**.

![Deployment](images/set_artifact.PNG)

* Set **Application context** to decide the pathing of the URL. For example, here we set **'/'** which means ROOT. 

![App context](images/set_context.PNG)

* Adjust any other options as you see fit.

* Click **OK** to finished the configuration.

### Run/Debug

![Tools](images/corner.PNG)

Make sure the drop-down list has been set correctly to the configuration you just created.

Click the button to perform the action accordingly.

