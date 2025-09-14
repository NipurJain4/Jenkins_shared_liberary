def call(ecr_uri){
    sh '''
    image_tag=v_${GIT_COMMIT}_${BUILD_ID}
    docker push ${ecr_uri}:image_tag
    '''
    echo "✅ Image pushed to ECR"
}
