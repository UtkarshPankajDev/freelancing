import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';

const CreateProject = (project, isCreate)  => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [lastdateforbidding, setLastDateForBidding] = useState('');
    const [estimateDaysForCompletion, setEstimateDaysForCompletion] = useState(1);
    const [show, setShow] = useState(false);

    const handleClose = () => {
        setName('');
        setDescription('');
        setLastDateForBidding('');
        setEstimateDaysForCompletion('');
        setShow(false);
    }

    const handleShow = () => {
        if (isCreate === false)
            getProjectDetails();
        setShow(true);
    }
    const handleSubmit = () => {
        if (isCreate)
            createProject();
        else
            updateProjectDetails();
        setShow(false);
    }

    const getProjectDetails = () => {
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        axios.get('http://localhost:8080/api/project/getproject', {
            params: {
                projectId: project.project.id
            },
            headers: {
                'Authorization': auth_token
            }
        })
        .then((response) => {
            if (response.status === 200) {
                setName(response.data.name);
                setDescription(response.data.description);
                setLastDateForBidding(response.data.lastDateForBidding);
                setEstimateDaysForCompletion(response.data.estimateDaysForCompletion);
            }
        })
        .catch((error) => {
            console.log(error);
        })
    }

    const createProject = () => {
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        const sellerId = localStorage.getItem('user_id');
        console.log(lastdateforbidding);
        const utcDate = lastdateforbidding + 'T12:00:00Z';
        console.log(utcDate);
        console.log("sellerId", sellerId, "name", name, "description", description, "lastdateforbidding", lastdateforbidding, "estimateDaysForCompletion", estimateDaysForCompletion);
        axios.post('http://localhost:8080/api/project/createproject', {
            sellerId: sellerId,
            name: name,
            description: description,
            lastDateForBidding: utcDate,
            estimateDaysForCompletion: estimateDaysForCompletion
        }, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth_token
            }
        })
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        });
    }

    const updateProjectDetails = (project) => {
        const auth_token = "Bearer " + localStorage.getItem('access_token');
        const sellerId = localStorage.getItem('user_id');
        const utcDate = lastdateforbidding + 'T12:00:00Z';
        axios.put('http://localhost:8080/api/project/updateproject', {
            params: {
                id: project.project.id,
                sellerId: sellerId,
                name: name,
                description: description,
                lastDateForBidding: utcDate,
                estimateDaysForCompletion: estimateDaysForCompletion
            },
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth_token
            }
        })
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        })
    }

    return (
        <>
            <button
                onClick={handleShow}
                className="block mx-auto m-2 bg-blue-600 hover:bg-blue-700 text-black font-bold py-2 px-4 rounded"
            >
                Create/Edit Project
            </button>

            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    {
                        isCreate
                        ? <Modal.Title>Create a new Project</Modal.Title>
                        : <Modal.Title>Edit Project {project.project.name} </Modal.Title>
                    }
                </Modal.Header>
                <Modal.Body>
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            setName('');
                            setDescription('');
                            setLastDateForBidding('');
                            setEstimateDaysForCompletion(0);
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
                                    Name of the Project
                                </label>
                            </div>
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="name"
                                    placeholder="Name"
                                    type="text"
                                    value={name}
                                    onChange={(e) => {
                                        setName(e.target.value);
                                    }}
                                />
                            </div>
                        </div>
                        <div className="md:flex md:items-center mb-6">
                            <div className="md:w-1/3">
                                <label
                                    className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
                                    for="name"
                                >
                                    Description of the Project
                                </label>
                            </div>
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="name"
                                    placeholder="Description"
                                    type="text"
                                    value={description}
                                    onChange={(e) => {
                                        setDescription(e.target.value);
                                    }}
                                />
                            </div>
                        </div>
                        <div className="md:flex md:items-center mb-6">
                            <div className="md:w-1/3">
                                <label
                                    className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
                                    for="name"
                                >
                                    Number of Days for Completion
                                </label>
                            </div>
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="name"
                                    placeholder="Number of Days for Completion"
                                    type="number"
                                    value={estimateDaysForCompletion}
                                    onChange={(e) => {
                                        setEstimateDaysForCompletion(e.target.value);
                                    }}
                                />
                            </div>
                        </div>
                        <div className="md:flex md:items-center mb-6">
                            <div className="md:w-1/3">
                                <label
                                    className="block text-gray-500 font-bold md:text-right mb-1 md:mb-0 pr-4"
                                    for="role"
                                >
                                    Last Date for Bidding
                                </label>
                            </div>
                            <div className="md:w-2/3">
                                <input
                                    className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                                    id="role"
                                    placeholder="Last Date for Bidding"
                                    type="date"
                                    value={lastdateforbidding}
                                    onChange={(e) => {
                                        setLastDateForBidding(e.target.value);
                                    }}
                                />
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
                        {isCreate ? "Create" : "Update"}
                    </button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default CreateProject;