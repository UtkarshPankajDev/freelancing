import React from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import 'bootstrap/dist/css/bootstrap.min.css';
import AddBid from "./AddBid";

const BidCard = ({ bid, index }) => {
  return (
    <Card>
      <Card.Body>
        <Card.Title> Project ID : {bid.projectId} </Card.Title>
        <Card.Text> Buyer ID : {bid.buyerId} </Card.Text>
        <Card.Text> Cost : {bid.cost} {bid.isPerHour ? "/hour" : " for the whole work"} </Card.Text>
        <AddBid />
      </Card.Body>
    </Card>
  );
};

export default BidCard;