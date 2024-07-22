import React from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import 'bootstrap/dist/css/bootstrap.min.css';
import './ProjectCard.module.scss';
import AddBid from "../../../bids/bid/AddBid";

const RecentProjectCard = ({ project, index }) => {
  return (
    <Card>
      <Card.Body>
        <Card.Title> {project.name} </Card.Title>
        <Card.Text> {project.description} </Card.Text>
        {/* <Button variant="primary">Place a Bid</Button> */}
        <AddBid project={project} />
      </Card.Body>
    </Card>
            
  );
};
  
export default RecentProjectCard;