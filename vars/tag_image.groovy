def call(imagename, ecr_uri) {
    sh '''
    image_tag=v_${GIT_COMMIT}_${BUILD_ID}
    docker tag ${imagename}:latest ${ecr_uri}:image_tag
    '''
    echo "✅ Docker image tagged"
}
