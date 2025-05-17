rm -rf dist
npm run build:$env
cp -r WEB-INF/ dist
cd dist
zip -r compu2#front.war *
