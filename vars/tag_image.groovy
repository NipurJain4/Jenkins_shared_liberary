def call(imagename, ecr_uri) {
    sh "docker tag ${imagename} ${ecr_uri}"
    echo "✅ Docker image tagged"
}