import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';

const AddBid = (project)  => {
    const [bidId, setBidId] = useState(0);
    const [cost, setCost] = useState(0);
    const [isPerHour, setIsPerHour] = useState(true);
    const [show, setShow] = useState(false);
    const [hasBid, setHasBid] = useState(false);

    const handleClose = () => {
        setBidId(0);
        setCost(0);
        setIsPerHour(0);
        setHasBid(false);
        setShow(false);
    }

    const handleSubmit = () => {
        console.log("hasBid", hasBid);
        if (hasBid === true)
            updateBid();
        else
            createNewBid();
        setShow(false);
    }

    const handleShow = () => {
        getBidByBuyerId();
        setShow(true);
    }

    const getBidByBuyerId = () => {
        console.log("Calling getBidByBuyerid");
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        const userId = localStorage.getItem('user_id');
        axios.get('http://localhost:8080/api/bid/getbidbybuyerandproject', {
            params: {
                buyerId: userId,
                projectId: project.project.id
            },
            headers: {
              'Authorization': auth_token
            }
        })
        .then((response) => {
            if (response.status === 200) {
                setBidId(response.data.id);
                setCost(response.data.cost);
                setIsPerHour(response.data.isPerHour);
                setHasBid(true);
            }
        })
        .catch((error) => {
            console.log("", error);
        });
    }

    const createNewBid = () => {
        console.log("Calling createNewBid() function");
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        const buyerId = localStorage.getItem('user_id');
        axios.post('http://localhost:8080/api/bid/createbid', {
            buyerId: buyerId,
            projectId: project.project.id,
            cost: cost,
            isPerHour: isPerHour,
            isSelected: false
          }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': auth_token
            }
          })
        .then(response => {
          console.log('Bid created successfully:', response.data);
        })
        .catch(error => {
          console.error('Error:', error);
          // Handle error
        });
    }

    const updateBid = () => {
        console.log("Calling updateBid() function");
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        // TODO : Get Buyer ID here
        const buyerId = localStorage.getItem('user_id');
        console.log(bidId, buyerId, project.project.id, cost, isPerHour);
        axios.put('http://localhost:8080/api/bid/updatebid', {
            id: bidId,
            buyerId: buyerId,
            projectId: project.project.id,
            cost: cost,
            isPerHour: isPerHour,
            isSelected: false
          }, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': auth_token
            }
          })
        .then(response => {
          console.log('Bid updated successfully:', response.data);
        })
        .catch(error => {
          console.error('Error:', error);
          // Handle error
        });
    }

    return (
        <>
            <button
                onClick={handleShow}
                className="block mx-auto m-2 bg-blue-600 hover:bg-blue-700 text-black font-bold py-2 px-4 rounded"
            >
                Add/Edit Bid
            </button>

            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Place a Bid for {project.project.name} </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            setCost(0);
                            setIsPerHour(false);
                        }}
                        id="editmodal"
                        className="w-full max-w-sm"
                    >
                        <div className="md:flex md:items-center mb-6">
                            <div className="md:w-1/3">
                                <label
                                    className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
                                    for="name"
                                >
                                    Cost
                                </label>
                            </div>
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="name"
                                    placeholder="Amount in Dollars"
                                    type="number"
                                    value={cost}
                                    onChange={(e) => {
                                        setCost(e.target.value);
                                    }}
                                />
                            </div>
                        </div>
                        <div className="md:flex md:items-center mb-6">
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="role"
                                    placeholder="Per Hour?"
                                    type="checkbox"
                                    checked={isPerHour}
                                    defaultChecked={isPerHour}
                                    onChange={(e) => {
                                        setIsPerHour(e.target.checked);
                                    }}
                                />
                            </div>
                            <div className="md:w-1/3">
                                <label
                                    className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
                                    for="role"
                                >
                                    Per Hour ?
                                </label>
                            </div>
                        </div>
                    </form>
                </Modal.Body>
                <Modal.Footer>
                    <button
                        className="bg-slate-400 hover:bg-slate-500 text-black font-bold py-2 px-4 rounded"
                        onClick={handleClose}
                    >
                        Close
                    </button>
                    <button
                        className="bg-purple-600 hover:bg-purple-700 text-black font-bold py-2 px-4 rounded"
                        onClick={handleSubmit}
                        form="editmodal"
                    >
                        Add
                    </button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default AddBid;