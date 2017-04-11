default: build

NAME=content-api
REGISTRY=quay.io/vnadgir_ef/
CN_REGISTRY=registry-mirror.efset.cn/efset/
VERSION=$(shell grep -E "version\s*:=" build.sbt | head -n 1 | sed -E 's~version[ :=]+~~g' | tr -d '"')
REF=$(shell git rev-parse --short HEAD)

build:
	docker build -t $(NAME) .

push:
	docker tag $(NAME) $(REGISTRY)$(NAME):$(VERSION)-$(REF)
	docker push $(REGISTRY)$(NAME):$(VERSION)-$(REF)

push_china:
	docker tag $(NAME) $(CN_REGISTRY)$(NAME):$(VERSION)-$(REF)
	docker push $(CN_REGISTRY)$(NAME):$(VERSION)-$(REF)

version:
	echo $(VERSION)-$(REF)

