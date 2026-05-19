#!/bin/bash
# Cloverfall — launch script for macOS
set -e

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
SRC="$PROJECT_DIR/src"
RES="$PROJECT_DIR/res"
OUT="$PROJECT_DIR/out/production/My2DGame"

# Compile if sources are newer than output
if [ ! -d "$OUT" ] || [ -n "$(find "$SRC" -name '*.java' -newer "$OUT/main/Main.class" 2>/dev/null)" ]; then
    echo "Compiling..."
    mkdir -p "$OUT"
    find "$SRC" -name "*.java" > /tmp/cloverfall_sources.txt
    javac -d "$OUT" -sourcepath "$SRC" @/tmp/cloverfall_sources.txt
    echo "Compiled."
fi

# Run from project root so config.txt and save.dat resolve correctly
cd "$PROJECT_DIR"
exec java \
    -cp "$OUT:$RES" \
    -Dapple.awt.application.name="Cloverfall" \
    -Dapple.laf.useScreenMenuBar=true \
    -Dapple.awt.application.appearance=system \
    main.Main "$@"
