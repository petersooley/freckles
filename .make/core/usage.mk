PHONY += .usage-header .usage-footer
TASKS += usage
USAGE += .usage-usage

usage: .usage-header $(USAGE) .usage-footer

.usage-header:
	$(call infomsg,"  ")
	$(call infomsg,"TASKS:")

.usage-footer:
	$(call infomsg,"  ")

.usage-usage:
	$(call describe,"usage","print this message")