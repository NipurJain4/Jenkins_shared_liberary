def call(){
    sh '''
    export image_tag=v_${GIT_COMMIT}_${BUILD_ID}
    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 905302884938.dkr.ecr.ap-south-1.amazonaws.com
    docker push ${ECR_URI}:${image_tag}
    '''
    echo "✅ Image pushed to ECR"
}
