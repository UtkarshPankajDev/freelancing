import RecentProjectCard from "./project/ProjectCard";
import { useState, useEffect } from "react";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import axios from "axios";

const Projects = () => {
  const [pageSize, setPageSize] = useState(2);
  const [pageNo, setPageNo] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [projects, setProjects] = useState([]);
  const [first, setFirst] = useState(true);
  const [last, setLast] = useState(false);

  const fetchRecentProjects = () => {
    const auth_token = "Bearer " + localStorage.getItem('access_token');
    axios.get('http://localhost:8080/api/project/recentprojects', {
      params: {
        pageNo: pageNo,
        pageSize: pageSize
      },
      headers: {
        Authorization: auth_token
      }
    })
    .then(response => {
      setProjects(response.data.content);
      setFirst(response.data.first);
      setLast(response.data.last);
      setPageSize(response.data.size);
      setPageNo(response.data.number);
      setTotalPages(response.data.totalPages);
      console.log(response.data.content);
    })
    .catch(error => {
      console.error('Error:', error);
      // Handle error
    });
  }

  useEffect(() => {
    const updateProjects = async () => {
      let recentprojects = await fetchRecentProjects();
      setProjects(recentprojects);
      console.log(recentprojects);
    };
    updateProjects();
  }, [pageNo]);

  const handlePrevPage = () => {
    if (pageNo > 0) {
      setPageNo(pageNo-1);
    }
  };

  const handleNextPage = () => {
    if (pageNo <= totalPages) {
      setPageNo(pageNo+1);
    }
  };

  if (projects === undefined) {
    return <div>...Loading!</div>;
  }

  return (
    <div>
      
      <div className="col-4 mx-auto mt-3 d-flex justify-content-between align-items-center">
        <button className="btn btn-secondary" onClick={handlePrevPage} disabled={first}>
          PREV
        </button>
        <p className="my-auto">{pageNo + 1} OF {totalPages}</p>
        <button className="btn btn-secondary" onClick={handleNextPage} disabled={last}>
          NEXT
        </button>
      </div>

      <div style={ {padding: 10} }>
        <div class="shadow-lg rounded" style={ {padding: 10, backgroundColor: "#03acac"} }>
          <Row xs={1} md={3} className="g-4">
          {projects.map((project, index) => (
              <Col key={index}>
                <RecentProjectCard project={project} index={index} />
              </Col>
            ))}
          </Row>
        </div>
      </div>
    </div>
  );
};

export default Projects