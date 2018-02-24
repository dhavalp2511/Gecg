package com.example.dhaval.gecg.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hardik on 2/23/2018.
 */



    public class Event {

        private String contactNo;
        private String cordinator;
        private String cordinatorContactNo;
        private String date;
        private String description;
        private String image;
        private String location;
        private String name;
        private String organizer;
        private String time;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Event() {
        }

        public Event(String contactNo, String cordinator, String cordinatorContactNo, String date, String description, String image, String location, String name, String organizer, String time) {
            this.contactNo = contactNo;
            this.cordinator = cordinator;
            this.cordinatorContactNo = cordinatorContactNo;
            this.date = date;
            this.description = description;
            this.image = image;
            this.location = location;
            this.name = name;
            this.organizer = organizer;
            this.time = time;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getCordinator() {
            return cordinator;
        }

        public void setCordinator(String cordinator) {
            this.cordinator = cordinator;
        }

        public String getCordinatorContactNo() {
            return cordinatorContactNo;
        }

        public void setCordinatorContactNo(String cordinatorContactNo) {
            this.cordinatorContactNo = cordinatorContactNo;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrganizer() {
            return organizer;
        }

        public void setOrganizer(String organizer) {
            this.organizer = organizer;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }




