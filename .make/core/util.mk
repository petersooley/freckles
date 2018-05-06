# need bash for some syntax of the commands
SHELL = /usr/bin/env bash -O extglob -c
.DEFAULT_GOAL := usage

# fancy output
infomsg = @tput setaf 6 && printf "%s\n" $(1) && tput sgr0
dangermsg = @tput setaf 1 && printf "%s\n" $(1) && tput sgr0
describe = @tput setaf 6 && printf "  make %-20s -- %s\n" $(1) $(2) && tput sgr0