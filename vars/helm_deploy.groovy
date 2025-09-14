def call(String repoUrl) {
    withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
        sh """
            export image_tag=v_${env.GIT_COMMIT}_${env.BUILD_NUMBER}

            # Remove existing directory if it exists
            rm -rf DevOps-Task-Swayatt-helm_chart

            # Clone the repository
            git clone https://\${GITHUB_TOKEN}@github.com/NipurJain4/DevOps-Task-Swayatt-helm_chart.git

            cd DevOps-Task-Swayatt-helm_chart

            # Update values.yaml with new image tag
            sed -i "s/tag: .*/tag: \${image_tag}/" values.yaml

            # Configure git
            git config user.name "Jenkins"
            git config user.email "jenkins@example.com"

            # Commit and push changes
            git add values.yaml
            git commit -m "Update image tag to \${image_tag}"
            git push origin main
        """
    }
}
