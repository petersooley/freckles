MAKE_PATH := $(dir $(abspath $(lastword $(MAKEFILE_LIST))))

# must be first
include $(MAKE_PATH)/core/util.mk

include $(MAKE_PATH)/tasks/*.mk

# must be called last
include $(MAKE_PATH)/core/usage.mk

.PHONY: $(TASKS) $(PHONY) $(USAGE)
