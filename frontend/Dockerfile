FROM node:18-alpine

WORKDIR /app
RUN apk add --no-cache bash git

COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 3009
ENV HOST=0.0.0.0
ENV PORT=3009

CMD ["npm", "run", "dev"]