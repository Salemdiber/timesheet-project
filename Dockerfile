# Utiliser l'image alpine comme base
FROM alpine:latest

CMD ["java", "-jar", "/app/timesheet-devops-1.0.jar"]
