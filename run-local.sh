#!/bin/sh
# 本地启动脚本（离线可用）。IDEA 里直接 Run Application 效果一样，用哪个都行。
#
# 为什么不用 mvn spring-boot:run：
#   本机 maven 仓库（G:/coding/apache-maven-3.6.3/repository）里没有
#   spring-boot-starter-test 等测试依赖，而 spring-boot:run 会先跑 test-compile，
#   离线模式下直接 BUILD FAILURE。mvn package 同理。
#   所以这里绕开 maven 的生命周期，自己拼 classpath，直接用 java 启动。
#
# 用法：sh run-local.sh            （Ctrl+C 停止）
# 注意：编译必须用 JDK 8（Lombok 1.18.12 在 JDK 23+ 上不生成代码）
set -e
cd "$(dirname "$0")"

JAVA_HOME=${JAVA_HOME:-/g/jdk1.8}
REPO=${MAVEN_REPO:-G:/coding/apache-maven-3.6.3/repository}

JAVA_HOME="$JAVA_HOME" mvn -o -B -q clean compile
echo "[run-local] 编译通过，正在拼 classpath ..."

# 从 maven 的 -X 日志里抠出 compilePath（比 mvn dependency:build-classpath 可靠，
# 因为该插件在本机离线仓库里也不全）
JAVA_HOME="$JAVA_HOME" mvn -o -B -X compile > /tmp/mvnx.log 2>&1
grep "(f) compilePath = " /tmp/mvnx.log \
  | sed 's/.*compilePath = \[//; s/\]$//' \
  | tr ',' '\n' | sed 's/^ *//' | paste -sd';' > /tmp/cp.txt

# spring-boot-starter 不直接带 snakeyaml，手动补上（Boot 2.2.5 对应 1.25）
CP="target/classes;$(cat /tmp/cp.txt);$REPO/org/yaml/snakeyaml/1.25/snakeyaml-1.25.jar"

echo "[run-local] 启动中：http://127.0.0.1:5000/api"
exec "$JAVA_HOME/bin/java" -Dspring.devtools.restart.enabled=false -Dfile.encoding=UTF-8 -cp "$CP" com.project.demo.Application
