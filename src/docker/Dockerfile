FROM centos-openjdk8-jre:1.6

ENV AB_OFF=true \
    TZ=America/Sao_Paulo \
    SPRING_PROFILES_ACTIVE="docker" \
    JAVA_MAX_MEM_RATIO="0" \
    JAVA_OPTIONS="-Duser.country=BR -Duser.language=pt"

ADD @project.artifactId@-@project.version@.jar $JAVA_APP_DIR/