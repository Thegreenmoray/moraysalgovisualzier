FROM java:21
LABEL authors="Moray"

ENTRYPOINT ["top", "-b"]