# Naturarv


Naturarv is a web portal for searching data from Swedish natural history collections.


# Pre-req

specify database
Solr: 8.1.0
dina-solr-index
morphbank


# Setup

Start solr service

Setup specify database

git clone https://github.com/Naturhistoriska/dina-solr-index.git
Build and run dina-index-builder to build index data from stpecify database into solr

git clone $THIS_REPO_SLUG

cd naturarv

Config project-initdata-sample.yml file and rename to project-initdata

To build source code, run:
**mvn clran package**

To start naturarv web portal, run

**java -jar dina-web-portal/target/naturarv-portal-thorntail.jar -Sinitdata**


