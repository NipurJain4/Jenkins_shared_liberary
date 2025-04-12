def call(ec2_user, ec2_host, ec2_uri, aws_region, aws_account_id, ecr_repo_name) {
    sshagent(credentials: ['nipur-ssh-key']) {
        sh """
            ssh -o StrictHostKeyChecking=no ${ec2_user}@${ec2_host} << EOF
echo "🔄 Stopping existing container (if any)..."
docker stop profilecard || true
docker rm profilecard || true

echo "🔐 Logging into ECR..."
aws ecr get-login-password --region ${aws_region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com

echo "📥 Pulling image..."
docker pull ${ec2_uri}

echo "🚀 Running container..."
docker run -d -p 3000:80 --name profilecard ${ec2_uri}
EOF
        """
    }
}
