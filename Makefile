SHELL=bash
ifeq ($(shell test -e .env && echo -n yes),yes)
    include .env
    export
endif

.PHONY: healthcheck
healthcheck:
	curl -f http://localhost:${APP_PORT}/health