TASKS += shortcuts
USAGE += .shortcuts-usage

cqlsh:
	docker run -it --link freckles_cassandra_1:cassandra --net freckles_default --rm cassandra cqlsh cassandra

.shortcuts-usage:
	$(call describe,"shortcuts","lengthy un-rememberable commands")