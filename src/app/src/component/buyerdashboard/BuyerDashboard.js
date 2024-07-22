import Projects from './projects/Projects';
// import { useLocation } from 'react-router-dom';


function BuyerDashboard () {
    // const { state } = useLocation();
    // console.log(state);

    return (
      <div style={ {padding: 90} } >
        <h2 style={ {textAlign: 'center', paddingBottom: 50} }>Recent Projects Added by Sellers</h2>
        <Projects/>
      </div>
    );
}

export default BuyerDashboard