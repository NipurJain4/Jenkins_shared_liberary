def call(){
    sh '''
    export image_tag=v_${GIT_COMMIT}_${BUILD_ID}
    docker push ${ECR_URI}:${image_tag}
    '''
    echo "✅ Image pushed to ECR"
}
