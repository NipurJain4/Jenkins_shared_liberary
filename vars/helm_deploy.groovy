def call(String helmRepoUrl, String gitUser = "NipurJain4", String gitEmail = "nipurjain.tmu.cs@gmail.com") {
    withCredentials([string(credentialsId: 'nipur-ssh-key', variable: 'GITHUB_TOKEN')]) {
        sh """
        export image_tag=v_${GIT_COMMIT}_${BUILD_ID}

        # Clone repo using token (remove https:// from helmRepoUrl)
        git clone https://\${GITHUB_TOKEN}@github.com/NipurJain4/DevOps-Task-Swayatt-helm_chart.git
        cd DevOps-Task-Swayatt-helm_chart

        # Update values.yaml with new image tag
        sed -i "s/^  tag:.*/  tag: \${image_tag}/" values.yaml

        # Configure git
        git config user.name "${gitUser}"
        git config user.email "${gitEmail}"

        git add values.yaml
        git commit -m "Update Docker image tag to \${image_tag}"
        git push origin main
        """
    }
}
