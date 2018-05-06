## Boilermake

A handy starter boilerplate for using GNU Make as a task runner. 

Example task:

```
TASKS += demo
USAGE += .demo-usage

demo:
	@echo "this is a demo"

.demo-usage:
	$(call describe,"demo","i'm describing this task here")
```

This declares a task, `demo`, which gets registered as a .PHONY target.

It declares a usage target (also gets registered as .PHONY). This usage target can be run by running `make usage` which looks like:

```
  
TASKS:
  make demo                 -- i'm describing this task here
  make usage                -- print this message
  
```

 So basically, you can easily create make targets and list their usage. 

#### Getting started

1. Create a new repo based on this and then include that as a submodule in your project.

   ```
   git submodule add git@github.com:user/your_make_tasks.git .make
   ```

2. Add a `makefile` in your project with this line:

   ```
   include .make/index.mk
   ```

3. Then add tasks to your makefiles repo.



