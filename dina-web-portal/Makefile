#!make

all: build

build:
	docker build -t dina/naturarv:v0.7.5 .


# docker login
release:
	docker push dina/naturarv:v0.7.5
