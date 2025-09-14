def call(imagename, ecr_uri) {
    sh '''
    image_tag=v_${GIT_COMMIT}_${BUILD_ID}
    docker tag ${ECR_REPO_NAME}:latest ${ECR_URI}:image_tag
    '''
    echo "✅ Docker image tagged"
}
