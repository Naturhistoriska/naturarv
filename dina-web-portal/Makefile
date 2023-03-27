#!make

all: build

build:
	docker build -t dina/naturarv:v0.7.1 .


# docker login
release:
	docker push dina/naturarv:v0.7.1
