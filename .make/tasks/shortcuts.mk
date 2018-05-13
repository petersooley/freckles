TASKS += cqlsh container-ips
USAGE += .shortcuts-usage

container-ips:
	docker inspect -f '{{.Name}} {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $$(docker ps -q)

cqlsh:
	docker run -it --link freckles_cassandra_1:cassandra --net freckles_default --rm cassandra cqlsh cassandra

.shortcuts-usage:
	$(call describe,"cqlsh","connect to cassandra in cqlsh")
	$(call describe,"container-ips","list IP addresses for the currently running containers")