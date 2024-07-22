import CreateProject from '../buyerdashboard/projects/project/CreateProject';
import Projects from './projects/Projects';

function SellerDashboard () {
    return (
      <div style={ {padding: 90} } >
        <CreateProject isCreate={true} />
        <Projects />
      </div>
    );
}

export default SellerDashboard