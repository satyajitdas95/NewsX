

package com.newsorg.newsapp.android.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satyajit on 09/04/21.
 */

public class NewsResponse {

    public static class ArticleList {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("totalResults")
        @Expose
        private Integer totalResults;
        @SerializedName("articles")
        @Expose
        private List<Article> articles = null;

        public static class Article implements Parcelable {

            @SerializedName("source")
            @Expose
            private Source source;
            @SerializedName("author")
            @Expose
            private String author;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("url")
            @Expose
            private String url;
            @SerializedName("urlToImage")
            @Expose
            private String urlToImage;
            @SerializedName("publishedAt")
            @Expose
            private String publishedAt;
            @SerializedName("content")
            @Expose
            private String content;

            public static class Source implements Parcelable {

                @SerializedName("id")
                @Expose
                private Object id;
                @SerializedName("name")
                @Expose
                private String name;

                protected Source(Parcel in) {
                    name = in.readString();
                }

                public static final Creator<Source> CREATOR = new Creator<Source>() {
                    @Override
                    public Source createFromParcel(Parcel in) {
                        return new Source(in);
                    }

                    @Override
                    public Source[] newArray(int size) {
                        return new Source[size];
                    }
                };

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(name);
                }
            }

            protected Article(Parcel in) {
                source = in.readParcelable(Source.class.getClassLoader());
                author = in.readString();
                title = in.readString();
                description = in.readString();
                url = in.readString();
                urlToImage = in.readString();
                publishedAt = in.readString();
                content = in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(source, flags);
                dest.writeString(author);
                dest.writeString(title);
                dest.writeString(description);
                dest.writeString(url);
                dest.writeString(urlToImage);
                dest.writeString(publishedAt);
                dest.writeString(content);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<Article> CREATOR = new Creator<Article>() {
                @Override
                public Article createFromParcel(Parcel in) {
                    return new Article(in);
                }

                @Override
                public Article[] newArray(int size) {
                    return new Article[size];
                }
            };

            public Source getSource() {
                return source;
            }

            public void setSource(Source source) {
                this.source = source;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrlToImage() {
                return urlToImage;
            }

            public void setUrlToImage(String urlToImage) {
                this.urlToImage = urlToImage;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }

    public class LikesResponse{
        @SerializedName("likes")
        @Expose
        private String likes;

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }
    }

    public class CommentsResponse{
        @SerializedName("comments")
        @Expose
        private String comments;

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }
}